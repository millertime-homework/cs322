	.global main
	.align 4
main:
!locals=1, max_args=2
	save %sp,-96,%sp
! [MOVE (TEMP 1) (CALL (NAME malloc) ( (BINOP * (CONST 4) (NAME wSZ))))]
	mov 4,%l0
	mov 4,%l1
	smul %l0,%l1,%l0
	mov %l0,%o0
	call malloc
	nop
	mov %o0,%l0
!>> Temp t1 assigned to reg %l1
	mov %o0,%l1
! [MOVE (MEM (TEMP 1)) (CONST 2)]
	mov 2,%l0
	st %l0,[%l1]
! [MOVE (MEM (BINOP + (TEMP 1) (NAME wSZ))) (FIELD (TEMP 1) 0)]
	mov %l1,%l0
	ld [%l0],%l2
	mov %l1,%l3
	mov 4,%l4
	add %l3,%l4,%l3
	st %l2,[%l3]
! [MOVE (MEM (BINOP + (TEMP 1) (BINOP * (CONST 2) (NAME wSZ)))) (CONST 3)]
	mov 3,%l2
	mov %l1,%l3
	mov 2,%l4
	mov 4,%l5
	smul %l4,%l5,%l4
	mov %l4,%l4
	add %l3,%l4,%l3
	st %l2,[%l3]
! [MOVE (MEM (BINOP + (TEMP 1) (BINOP * (CONST 3) (NAME wSZ)))) (FIELD (TEMP 1) 2)]
	mov %l1,%l2
	ld [%l2+2],%l3
	mov %l1,%l4
	mov 3,%l5
	mov 4,%l6
	smul %l5,%l6,%l5
	mov %l5,%l5
	add %l4,%l5,%l4
	st %l3,[%l4]
! [MOVE (VAR 1) (TEMP 1)]
	mov %l1,%l3
	st %l3,[%fp-4]
! [CALLST (NAME A_setk) ( (VAR 1) (CONST 5))]
	call A_setk
	nop
! [CALLST (NAME print) ( (FIELD (VAR 1) 1))]
	ld [%fp-4],%l3
	ld [%l3+1],%o1
	sethi %hi(L$1),%o0
	or %o0, %lo(L$1),%o0
	call printf
	nop
! [CALLST (NAME print) ( (FIELD (VAR 1) 2))]
	ld [%fp-4],%l4
	ld [%l4+2],%o1
	sethi %hi(L$1),%o0
	or %o0, %lo(L$1),%o0
	call printf
	nop
! [CALLST (NAME print) ( (FIELD (VAR 1) 3))]
	ld [%fp-4],%l5
	ld [%l5+3],%o1
	sethi %hi(L$1),%o0
	or %o0, %lo(L$1),%o0
	call printf
	nop
	ret
	restore

	.align 4
A_setk:
!locals=0, max_args=0
	save %sp,-96,%sp
! [MOVE (FIELD (PARAM 0) 2) (PARAM 1)]
	ld [%fp+72],%l0
	ld [%fp+68],%l1
	st %l0,[%l1+2]
	ret
	restore

L$1:	.asciz "%d\n"

!Total regs:  8
!Total insts: 70
