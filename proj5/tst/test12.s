	.global main
	.align 4
main:
!locals=1, max_args=1
	save %sp,-96,%sp
! [MOVE (TEMP 1) (CALL (NAME malloc) ( (NAME wSZ)))]
	mov 4,%o0
	call malloc
	nop
	mov %o0,%l0
!>> Temp t1 assigned to reg %l1
	mov %o0,%l1
! [MOVE (VAR 1) (TEMP 1)]
	mov %l1,%l0
	st %l0,[%fp-4]
! [MOVE (TEMP 4) (CALL (NAME Body_go) ( (VAR 1)))]
	ld [%fp-4],%l0
	st %l0,[%sp+68]
	call Body_go
	nop
	mov %o0,%l2
!>> Temp t4 assigned to reg %l3
	mov %o0,%l3
! [CALLST (NAME print) ( (TEMP 4))]
	ld [%l3],%o1
	sethi %hi(L$1),%o0
	or %o0, %lo(L$1),%o0
	call printf
	nop
	ret
	restore

	.align 4
Body_go:
!locals=1, max_args=0
	save %sp,-96,%sp
! [MOVE (TEMP 2) (CALL (NAME malloc) ( (BINOP * (CONST 3) (NAME wSZ))))]
	mov 3,%l0
	mov 4,%l1
	smul %l0,%l1,%l0
	mov %l0,%o0
	call malloc
	nop
	mov %o0,%l0
!>> Temp t2 assigned to reg %l1
	mov %o0,%l1
! [MOVE (MEM (TEMP 2)) (CONST 2)]
	mov 2,%l0
	st %l0,[%l1]
! [MOVE (TEMP 3) (BINOP + (TEMP 2) (BINOP * (CONST 2) (NAME wSZ)))]
	mov %l1,%l0
	mov 2,%l2
	mov 4,%l3
	smul %l2,%l3,%l2
	mov %l2,%l2
	add %l0,%l2,%l0
	mov %l0,%l0
!>> Temp t3 assigned to reg %l2
	mov %l0,%l2
! [LABEL L0]
L0:
! [MOVE (MEM (TEMP 3)) (CONST 0)]
	mov 0,%l0
	st %l0,[%l2]
! [MOVE (TEMP 3) (BINOP - (TEMP 3) (NAME wSZ))]
	mov %l2,%l0
	mov 4,%l3
	sub %l0,%l3,%l0
	mov %l0,%l0
	mov %l0,%l2
! [CJUMP > (TEMP 3) (TEMP 2) (NAME L0)]
	mov %l2,%l0
	mov %l1,%l3
	cmp %l0,%l3
	bg L0
	nop
! [MOVE (VAR 1) (TEMP 2)]
	mov %l1,%l0
	st %l0,[%fp-4]
! [MOVE (MEM (BINOP + (VAR 1) (NAME wSZ))) (CONST 1)]
	mov 1,%l0
	ld [%fp-4],%l3
	mov 4,%l4
	add %l3,%l4,%l3
	st %l0,[%l3]
! [MOVE (MEM (BINOP + (VAR 1) (BINOP * (CONST 2) (NAME wSZ)))) (CONST 2)]
	mov 2,%l0
	ld [%fp-4],%l3
	mov 2,%l4
	mov 4,%l5
	smul %l4,%l5,%l4
	mov %l4,%l4
	add %l3,%l4,%l3
	st %l0,[%l3]
! [RETURN (MEM (BINOP + (VAR 1) (BINOP * (CONST 2) (NAME wSZ))))]
	ret
	restore
	ret
	restore

L$1:	.asciz "%d\n"

!Total regs:  6
!Total insts: 77
