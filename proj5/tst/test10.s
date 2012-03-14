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
! [MOVE (TEMP 3) (CALL (NAME Body_go) ( (VAR 1)))]
	ld [%fp-4],%l0
	st %l0,[%sp+68]
	call Body_go
	nop
	mov %o0,%l2
!>> Temp t3 assigned to reg %l3
	mov %o0,%l3
! [CALLST (NAME print) ( (TEMP 3))]
	ld [%l3],%o1
	sethi %hi(L$1),%o0
	or %o0, %lo(L$1),%o0
	call printf
	nop
	ret
	restore

	.align 4
Body_go:
!locals=1, max_args=2
	save %sp,-96,%sp
! [MOVE (TEMP 2) (CALL (NAME malloc) ( (NAME wSZ)))]
	mov 4,%o0
	call malloc
	nop
	mov %o0,%l0
!>> Temp t2 assigned to reg %l1
	mov %o0,%l1
! [MOVE (MEM (TEMP 2)) (CONST 0)]
	mov 0,%l0
	st %l0,[%l1]
! [MOVE (VAR 1) (TEMP 2)]
	mov %l1,%l0
	st %l0,[%fp-4]
! [MOVE (TEMP 4) (CALL (NAME Body2_value) ( (VAR 1) (CONST 1)))]
	ld [%fp-4],%l0
	st %l0,[%sp+68]
	ld [1],%l2
	st %l2,[%sp+68]
	call Body2_value
	nop
	mov %o0,%l3
!>> Temp t4 assigned to reg %l4
	mov %o0,%l4
! [RETURN (TEMP 4)]
	ret
	restore
	ret
	restore

	.align 4
Body2_value:
!locals=2, max_args=0
	save %sp,-104,%sp
! [MOVE (FIELD (PARAM 0) 0) (CONST 5)]
	mov 5,%l0
	ld [%fp+68],%l1
	st %l0,[%l1]
! [MOVE (VAR 1) (CONST 6)]
	mov 6,%l0
	st %l0,[%fp-4]
! [CJUMP == (PARAM 1) (CONST 0) (NAME L0)]
	ld [%fp+72],%l0
	mov 0,%l2
	cmp %l0,%l2
	be L0
	nop
! [MOVE (VAR 2) (FIELD (PARAM 0) 0)]
	ld [%fp+68],%l0
	ld [%l0],%l2
	st %l2,[%fp-8]
! [JUMP (NAME L1)]
	ba L1
	nop
! [LABEL L0]
L0:
! [MOVE (VAR 2) (VAR 1)]
	ld [%fp-4],%l2
	st %l2,[%fp-8]
! [LABEL L1]
L1:
! [RETURN (VAR 2)]
	ret
	restore
	ret
	restore

L$1:	.asciz "%d\n"

!Total regs:  5
!Total insts: 73
